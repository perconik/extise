class GitEclipseOrg::User < ActiveRecord::Base
  alias_attribute :user_name, :username
  alias_attribute :real_name, :name

  has_many :change_list, class_name: :Change, foreign_key: :owner_id, dependent: :destroy
  has_many :messages, foreign_key: :author_id, dependent: :destroy
  has_many :labels, dependent: :destroy
end
