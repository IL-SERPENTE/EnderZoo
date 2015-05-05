package crazypants.enderzoo.gen;

import java.io.File;
import java.io.IOException;

import org.apache.commons.compress.utils.IOUtils;

import crazypants.enderzoo.IoUtil;
import crazypants.enderzoo.Log;
import crazypants.enderzoo.config.Config;
import crazypants.enderzoo.gen.io.StructureResourceManager;

public class EnderZooStructures {

  public static final File ROOT_DIR = new File(Config.configDirectory + "/structures/");
  public static final String RESOURCE_PATH = "/assets/enderzoo/config/structures/";

  public static void registerStructures() {
    StructureRegister reg = StructureRegister.instance;
    reg.getResourceManager().addResourcePath(ROOT_DIR);
    reg.getResourceManager().addResourcePath(RESOURCE_PATH);

    String fileName = "README.txt";
    copyText(fileName, fileName);

    register("test");
  }

  private static void copyText(String resourceName, String fileName) {
    try {
      IoUtil.copyTextTo(new File(ROOT_DIR, fileName), EnderZooStructures.class.getResourceAsStream(RESOURCE_PATH + resourceName));
    } catch (IOException e) {
      Log.warn("EnderZooStructures: Could not copy " + RESOURCE_PATH + resourceName + " from jar to " + ROOT_DIR.getAbsolutePath() + fileName + " Ex:" + e);
    }
  }

  private static void register(String uid) {
    
      String name = uid + StructureResourceManager.GENERATOR_EXT;
      copyText(name, name + ".defaultValues");

      StructureRegister reg = StructureRegister.instance;
      try {
        reg.registerGenerator(reg.getResourceManager().loadGenerator(uid));
      } catch (Exception e) {
        Log.error("EnderZooStructures: Could not load structure template " + uid + StructureResourceManager.GENERATOR_EXT);
        e.printStackTrace();
      }
    

  }

}
