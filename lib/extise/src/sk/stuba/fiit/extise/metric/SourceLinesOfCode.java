package sk.stuba.fiit.extise.metric;

import java.util.Collection;

import static java.util.Arrays.asList;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.size;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.isWhitespacePredicate;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.lines;

public final class SourceLinesOfCode extends NumericMetric<Integer> {
  @Override
  public Collection<Integer> apply(final String input) {
    return asList(size(filter(lines(input), not(isWhitespacePredicate()))));
  }
}
