package utils;
    public class GenericUtils {
        public static String getBaseUrl(String config, String protocolPropertyName, String baseUrlPropertyName) {
            return ConfigUtils.getGenericElement(config, protocolPropertyName) +
                    "://" + ConfigUtils.getGenericElement(config, baseUrlPropertyName);
        }
        public static String getBrowser(String config, String propertyName) {
            return ConfigUtils.getGenericElement(config, propertyName);
        }
    }


