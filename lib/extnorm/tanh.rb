class Extnorm::Tanh
  def initialize(v)
    @mean, @sd = v.mean, v.sd
  end

  def normalize(x)
    e = Math.exp -(x.to_f - @mean) / @sd
    v = (1 - e) / (1 + e)

    { mean: @mean, standard_deviation: @sd, value: v }
  end
end
