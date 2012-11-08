// Do not edit this file!  It was generated by Melati POEM's DSD preprocessor.

package org.paneris.melati.gvis.poem.generated;


// 10 tables in database
import org.melati.poem.UserTable;
import org.melati.poem.User;
import org.melati.poem.GroupTable;
import org.melati.poem.Group;
import org.melati.poem.CapabilityTable;
import org.melati.poem.Capability;
import org.melati.poem.GroupMembershipTable;
import org.melati.poem.GroupMembership;
import org.melati.poem.GroupCapabilityTable;
import org.melati.poem.GroupCapability;
import org.melati.poem.TableCategoryTable;
import org.melati.poem.TableCategory;
import org.melati.poem.TableInfoTable;
import org.melati.poem.TableInfo;
// abstract import org.melati.poem.ValueInfoTable;
// abstract import org.melati.poem.ValueInfo;
import org.melati.poem.ColumnInfoTable;
import org.melati.poem.ColumnInfo;
import org.melati.poem.SettingTable;
import org.melati.poem.Setting;

/**
 * Melati POEM generated base interface to the tables in 
 * org.paneris.melati.gvis.poem.
 */
public interface GvisDatabaseTablesBase {


 /**
  * Retrieves the UserTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the UserTable from this database
  */
  public UserTable<User> getUserTable();

 /**
  * Retrieves the GroupTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the GroupTable from this database
  */
  public GroupTable<Group> getGroupTable();

 /**
  * Retrieves the CapabilityTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the CapabilityTable from this database
  */
  public CapabilityTable<Capability> getCapabilityTable();

 /**
  * Retrieves the GroupMembershipTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the GroupMembershipTable from this database
  */
  public GroupMembershipTable<GroupMembership> getGroupMembershipTable();

 /**
  * Retrieves the GroupCapabilityTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the GroupCapabilityTable from this database
  */
  public GroupCapabilityTable<GroupCapability> getGroupCapabilityTable();

 /**
  * Retrieves the TableCategoryTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the TableCategoryTable from this database
  */
  public TableCategoryTable<TableCategory> getTableCategoryTable();

 /**
  * Retrieves the TableInfoTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the TableInfoTable from this database
  */
  public TableInfoTable<TableInfo> getTableInfoTable();

 /**
  * Retrieves the ColumnInfoTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the ColumnInfoTable from this database
  */
  public ColumnInfoTable<ColumnInfo> getColumnInfoTable();

 /**
  * Retrieves the SettingTable table.
  *
  * see org.melati.poem.prepro.TableDef#generateTableAccessorJava 
  * @return the SettingTable from this database
  */
  public SettingTable<Setting> getSettingTable();
}


