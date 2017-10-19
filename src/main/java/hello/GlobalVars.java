package hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 静态全局变量被系统回收，通过序列化到本地来解决
 */
public class GlobalVars implements Serializable {
    interface AppConstants {
        String CACHE_DIR = ".\\build\\";
    }

    public static void main(String[] args) {

        GlobalVars.getInstance().reset();

        {
            UserEntity user = new UserEntity();
            user.setName("andy");
            System.out.println(user);
            GlobalVars.getInstance().setUser(user);//序列化到文件
            user.setName("tomy");//只修改内存中的变量，没有序列化到文件
        }
        {

            GlobalVars.instance = null;//模拟静态全局变量被系统回收
            UserEntity user = GlobalVars.getInstance().getUser();

            System.out.println(user);
        }

    }

    private static final long serialVersionUID = -206433656585280519L;

    private volatile static GlobalVars instance;


    private final static String TAG = "GlobalVars1";
    private UserEntity user;

    private GlobalVars() {
    }

    public static GlobalVars getInstance() {
        if (instance == null) {
            Object object = Utils.restoreObject(AppConstants.CACHE_DIR + TAG);
            if (object == null) {
                object = new GlobalVars();
                Utils.saveObject(AppConstants.CACHE_DIR + TAG, object);
            }
            instance = (GlobalVars) object;
        }
        return instance;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        Utils.saveObject(AppConstants.CACHE_DIR + TAG, this);
    }

    public void reset() {
        user = null;
        Utils.saveObject(AppConstants.CACHE_DIR + TAG, this);
    }


    private static class UserEntity implements Serializable {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "user.name=" + name;
        }
    }

    private static class Utils {
        public static void saveObject(String path, Object saveObject) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            File f = new File(path);
            try {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(saveObject);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oos != null) {
                        oos.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static Object restoreObject(String path) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            Object object = null;
            File f = new File(path);
            if (!f.exists()) {
                return null;
            }
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                object = ois.readObject();
                return object;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return object;
        }
    }
}
