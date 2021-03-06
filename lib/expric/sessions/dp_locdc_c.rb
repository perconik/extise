class Expric::Sessions::DpLocdcC
  include Extric::Common
  include Expric::Shared

  def initialize
    @p = reuse_metric Extric::Sessions::LinesOfCodeDeltaCombination
  end

  def measure(user, session)
    p = fetch_value via: @p, of: user, on: session

    return unless p

    { value: calculate_interactive_productivity(on: session, volume: p) }
  end
end
