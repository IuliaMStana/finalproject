package utils;
    public class GenericUtils {
        public static String getBaseUrl(String config, String protocolPropertyName, String baseUrlPropertyName) {
            return ConfigUtils.getGenericElement(config, protocolPropertyName) +
                    "://" + ConfigUtils.getGenericElement(config, baseUrlPropertyName);
        }

        public static String getDbHostname(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }

        public static String getDbUser(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }

        public static String getDbPassword(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }

        public static String getDbSchema(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }

        public static String getDbPort(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }
        public static String getBrowser(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }
    }


