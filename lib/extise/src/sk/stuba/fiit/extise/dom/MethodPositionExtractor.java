package sk.stuba.fiit.extise.dom;

import sk.stuba.fiit.perconik.core.java.dom.NodeCollectors;

import static sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType.METHOD_DECLARATION;

public final class MethodPositionExtractor extends NodePositionExtractor {
  public MethodPositionExtractor() {
    super(NodeCollectors.ofType(METHOD_DECLARATION));
  }
}
