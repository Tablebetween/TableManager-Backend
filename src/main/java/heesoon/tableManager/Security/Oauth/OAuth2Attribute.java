package heesoon.tableManager.Security.Oauth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Slf4j
public class OAuth2Attribute {

    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private String nickname;
    private String pfUrl;
    private String sex;
    private String birth;
    private String age;
    private String provider;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        switch (provider) {
            case "kakao":
                return ofKakao("email", attributes, provider);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes, String provider) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        log.info("kakaoAccount = {}", kakaoAccount);
        log.info("kakaoProfile = {}", kakaoProfile);

        return OAuth2Attribute.builder()
                .name((String) kakaoAccount.get("email"))
                .nickname((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .pfUrl((String) kakaoProfile.get("profile_image_url"))
                .sex((String) kakaoAccount.get("gender"))
                .birth((String) kakaoAccount.get("birthday"))
                .age((String) kakaoAccount.get("age_range"))
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .provider(provider)
                .build();
    }

    Map<String, Object> convertToMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("email", email);
        map.put("pfUrl", pfUrl);
        map.put("sex", sex);
        map.put("birth", birth);
        map.put("age", age);
        map.put("provider", provider);

        return map;
    }
}
