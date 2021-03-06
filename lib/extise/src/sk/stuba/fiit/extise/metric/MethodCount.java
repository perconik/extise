package sk.stuba.fiit.extise.metric;

import java.util.Collection;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import sk.stuba.fiit.perconik.core.java.dom.NodeCounters;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.extise.Java.parse;

public final class MethodCount extends NumericMetric<Integer> {
  @Override
  public Collection<Integer> apply(final String input) {
    CompilationUnit unit = (CompilationUnit) parse(input);

    return asList(NodeCounters.ofClass(MethodDeclaration.class).apply(unit));
  }
}
