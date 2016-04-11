class CreateMessagesByGitEclipseOrg < ActiveRecord::Migration
  def change
    create_table :git_eclipse_org_messages do |t|
      t.references :change, null: false
      t.references :author, null: false

      t.string :identifier, null: false, limit: 17
      t.text :message, null: false
      t.datetime :date, null: false

      t.timestamps null: false
    end

    add_index :git_eclipse_org_messages, :change_id
    add_index :git_eclipse_org_messages, :author_id

    add_index :git_eclipse_org_messages, :identifier, unique: true, name: 'index_git_eclipse_org_messages_as_unique'
    add_index :git_eclipse_org_messages, :date
  end
end