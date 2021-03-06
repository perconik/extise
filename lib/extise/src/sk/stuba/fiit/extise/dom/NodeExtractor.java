package sk.stuba.fiit.extise.dom;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import sk.stuba.fiit.extise.Bootstrap;

import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaException;
import sk.stuba.fiit.perconik.utilities.function.ListCollector;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

import static sk.stuba.fiit.extise.Java.parse;

abstract class NodeExtractor extends Bootstrap.Unit<String> {
  private final ListCollector<ASTNode, ASTNode> collector;

  NodeExtractor(final ListCollector<ASTNode, ASTNode> collector) {
    this.collector = checkNotNull(collector);
  }

  static StringBuilder block(@Nullable final String file, final String path, final int line, final int offset, final int length) {
    StringBuilder block = new StringBuilder(128 + path.length());

    block.append("# ").append(file != null ? file : "?").append(":").append(path);
    block.append(":").append(line).append(" ").append(offset).append("+").append(length);

    return block;
  }

  @Override
  public final Collection<String> apply(final String input, @Nullable final String file) {
    try {
      CompilationUnit unit = (CompilationUnit) parse(input);

      List<ASTNode> nodes = this.collector.apply(unit);
      List<String> blocks = newArrayListWithCapacity(nodes.size());

      for (ASTNode node: nodes) {
        blocks.add(this.extract(file, input, unit, node));
      }

      return blocks;
    } catch (JavaException failure) {
      return asList(block(file, "!", 0, 0, 0).toString());
    }
  }

  abstract String extract(@Nullable String path, String input, CompilationUnit unit, ASTNode node);
}
