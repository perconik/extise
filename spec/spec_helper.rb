Dir[File.expand_path '../support/*.rb', __FILE__].each { |f| require_relative f }
Dir[File.expand_path '../**/concerns/*.rb', __FILE__].each { |f| require_relative f }
$LOAD_PATH.unshift File.expand_path('../../lib', __FILE__)
