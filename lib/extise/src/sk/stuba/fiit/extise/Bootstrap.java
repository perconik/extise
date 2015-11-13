package sk.stuba.fiit.extise;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import org.elasticsearch.common.base.Joiner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.Arrays.asList;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static com.google.common.collect.Lists.newArrayListWithExpectedSize;
import static com.google.common.io.Resources.getResource;

public final class Bootstrap {
  private Bootstrap() {}

  public static void run(final Function<? super Collection<String>, ? extends Collection<?>> fx, final String ... args) throws Exception {
    List<String> inputs;

    if (args.length != 0) {
      inputs = newArrayListWithCapacity(args.length);

      for (String arg: args) {
        inputs.add(Files.toString(new File(arg), UTF_8));
      }
    } else {
      inputs = asList(CharStreams.toString(new InputStreamReader(in, UTF_8)));
    }

    Collection<?> outputs = fx.apply(inputs);

    out.println(fx + " " + Joiner.on(" ").join(args));

    for (Object output: outputs) {
      out.println(output);
    }
  }

  static abstract class AbstractUnit<T> implements Function<Collection<String>, Collection<T>> {
    AbstractUnit() {}

    abstract Collection<T> apply(final String input);

    @Override
    protected final Object clone() throws CloneNotSupportedException {
      throw new CloneNotSupportedException();
    }

    @Override
    protected final void finalize() {}

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static abstract class Unit<T> extends AbstractUnit<T> {
    protected Unit() {}

    @Override
    public Collection<T> apply(final Collection<String> inputs) {
      List<T> output = newArrayListWithExpectedSize(16 * inputs.size());

      for (String input: inputs) {
        output.addAll(this.apply(input));
      }

      return output;
    }

    @Override
    protected Collection<T> apply(final String input) {
      throw new UnsupportedOperationException();
    }
  }

  public static String load(final String resource) {
    try {
      return Resources.toString(getResource(resource), UTF_8);
    } catch (IOException e) {
      throw propagate(e);
    }
  }
}
