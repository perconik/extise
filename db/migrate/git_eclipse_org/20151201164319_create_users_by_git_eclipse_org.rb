class CreateUsersByGitEclipseOrg < ActiveRecord::Migration
  def change
    create_table :git_eclipse_org_users do |t|
      t.string :name, null: false
      t.string :email, null: true

      t.timestamps null: false
    end

    add_index :git_eclipse_org_users, :name, unique: true, name: 'index_git_eclipse_org_users_as_unique'
    add_index :git_eclipse_org_users, :email
  end
end