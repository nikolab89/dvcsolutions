package helpers;


import controllers.ApplicationController;
import controllers.MyPreferencesManager;

/**
 * Created by Nikola on 7/23/2017.
 */

public class Utility {


   public static MyPreferencesManager PrefManager = ApplicationController.getInstance().getMyPreferencesManager();
}
