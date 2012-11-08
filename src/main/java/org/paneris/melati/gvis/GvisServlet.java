
package org.paneris.melati.gvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.melati.LogicalDatabase;
import org.melati.Melati;
import org.melati.MelatiConfig;
import org.melati.servlet.TemplateServlet;
import org.melati.template.ServletTemplateContext;
//import org.paneris.melati.gvis.poem.GvisDatabase;

import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.DataSourceHelper;
import com.google.visualization.datasource.DataTableGenerator;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.query.Query;
import com.google.visualization.datasource.util.SqlDataSourceHelper;
import com.google.visualization.datasource.util.SqlDatabaseDescription;

/**
 * Base servlet for Gvis servlets.
 */
public class GvisServlet 
    extends TemplateServlet implements DataTableGenerator{


  public static final String templatePrefix = "org/paneris/melati/gvis/view/";

  public String getSysAdminName () {
    return "TimP";
  }
  public String getSysAdminEmail () {
    return "timp@paneris.org";
  }

  /**
   * @see org.melati.servlet.ConfigServlet#doConfiguredRequest(org.melati.Melati)
   */
  protected void doConfiguredRequest(final Melati melati)
      throws ServletException, IOException {
    String pathInfo = melati.getRequest().getPathInfo();
    if (pathInfo == null) pathInfo = "";

    // check if pathinfo exists in filesystem
    // if so then redirect to it, unless we came from there
    while (pathInfo != "" && !fileAt(pathInfo)) {
      String s = pathInfo.substring(1);
      int i = s.indexOf('/');
      if (i != -1)
        pathInfo = s.substring(i);
      else
        pathInfo = "";
    }
    if (pathInfo != "") {
      System.err.println("pathinfo:" + pathInfo);
      System.err.println("Ref:" + melati.getRequest().getHeader("Referer"));
      String referer = melati.getRequest().getHeader("Referer");
      if (referer != null &&
          referer.indexOf(pathInfo) == -1) {
        melati.getResponse().sendRedirect(pathInfo);
        return;
      }
    }
    DataSourceHelper.executeDataSourceServletFlow(melati.getRequest(), melati.getResponse(), this, isRestrictedAccessMode());

    //super.doConfiguredRequest(melati);
  }
  private boolean fileAt(String filename){
    if (filename.equals("")) return false;
    if (filename.equals("/")) return false;
    String fsName = "/dist/gvis/www" + filename;
    File it = new File(fsName);
    System.err.println("FS:" + fsName + " " + it.exists());
    return it.exists();
  }

  public String gvisTemplate(String name) {
    return addExtension(templatePrefix + name);
  }

    // Override org.melati.TemplateServlet.addExtension()
    // to cope with heterogenous naming convention :)
  protected String addExtension(String templateName) {
    int index = templateName.indexOf(".wm");
    if (index == -1)
      templateName = templateName + ".wm";
    System.err.println("Template:" + templateName);
    return templateName;
  }

  /**
   * Concrete class for {@link TemplateServlet}.
   *
   * @param melati
   * @param context
   * @return Template name
   */
  protected String doTemplateRequest(Melati melati, ServletTemplateContext context)
      throws Exception {
    return "temp";
//    return gvisTemplate(reallyDoTemplateRequest(melati, context));
    
  }

  /**
   * Override this method to build up output in individual servlets.
   *
   * @return Template name without path or extension
   */
  /*
  protected abstract String
    reallyDoTemplateRequest(Melati melati,
                            ServletTemplateContext templateContext)
      throws Exception;
*/
  protected String getSetting(Melati melati, String settingName) {
    String returnString = melati.getDatabase().getSettingTable().get(settingName);
    if (returnString == null)
      throw new RuntimeException("Setting " + settingName + " not found in setting table");
    return returnString;
  }

  
  /**
   * Returns a flag that indicates whether the servlet is in restricted-access mode.
   * In restricted-access mode the server serves only requests coming from the same domain as the
   * server domain (i.e., same origin policy).
   * This protects the server from XSRF attacks while limiting the requests to which the server
   * can respond.
   *
   * @return True if this servlet operates in restricted-access mode, false otherwise.
   */
  protected boolean isRestrictedAccessMode() {
    return false;
  }
  
  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest request)
      throws DataSourceException {
    Melati melati = new Melati(new MelatiConfig(), request, null);
    melati.setPoemContext(poemContext(melati));
    
    String prefix =  LogicalDatabase.class.getName() + "." + melati.getDatabaseName() + ".";

    
    
    SqlDatabaseDescription dbDescription = new SqlDatabaseDescription(
        getOrDie(databaseDefs(), prefix + "url"),
        getOrDie(databaseDefs(), prefix + "user"),
        getOrDie(databaseDefs(), prefix + "pass"),
        melati.getPoemContext().getTable());
    return SqlDataSourceHelper.executeQuery(query, dbDescription);
  }
  @Override
  public Capabilities getCapabilities() {
    return Capabilities.SQL;
  }

  public static String getOrDie(Properties properties, String propertyName) {
    String value = properties.getProperty(propertyName);
    if (value == null)
      throw new RuntimeException("Property " + propertyName + " not found in " + properties);
    return value;
  }
  /** Properties, named for this class. */
  public static Properties databaseDefs = null;
  /**
   * @return the database defs
   */
  public static Properties databaseDefs() {
    if (databaseDefs == null)
      databaseDefs = getProperties();
    return databaseDefs;
  }
  /**
   * @return a properties object
   */
  public static Properties getProperties() {
    String name = "org.melati.LogicalDatabase.properties";
    InputStream is = Melati.class.getResourceAsStream(name);

    if (is == null)
      throw new RuntimeException(new FileNotFoundException(name + ": is it in CLASSPATH?"));

    Properties them = new Properties();
    try {
      them.load(is);
    } catch (IOException e) {
      throw new RuntimeException(new IOException("Corrupt properties file `" + name + "': " +
      e.getMessage()));
    }
    return them;
  }
  
}
