package mantis.appmanager;


public class RegistrationHelper extends HelperBase {


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+ "/signup_page.php");
    }
}
