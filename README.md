# Mentoring 2025년 2월

- 전체 3회차 중 3회차 (화요일, 일요일)

## Download Source Code

**소스 코드 다운로드**

```bash
git clone https://github.com/merge-simpson/mentoring-capitalizing-util-2025.git
```

## Install Library

다음처럼 `build.gradle.kts` 파일에 메이븐 저장소 및 의존성 라이브러리를 명시합니다.

```kotlin
repositories {
    // ...
    maven { url = "https://jitpack.io" }
}

dependencies {
    // ...
    implementation("com.github.merge-simpson:mentoring-capitalizing-util-2025:0.1.2-rc1")
}
```

# How This Has Been Tested

- Kotest FreeSpec in Usual

## Test Cases

### Capitalizing Utility

- UPPER_CASE_WITH_UNDERSCORES -> Correctly Capitalized
- \_SURROUNDED_WITH_UNDERSCORES\_ -> 앞뒤 언더스코어는 무시됩니다.
- CONTINUOUS__UNDERSCORES___BETWEEN_WORDS -> 연속되었던 언더스코어는 단일 공백(' ')으로 바뀝니다.
- CONTINUOUS__UNDERSCORES___TAIL_______ -> 마지막에 오는 언더스코어는 무시됩니다.
- _______CONTINUOUS__UNDERSCORES___HEAD -> 처음에 오는 언더스코어는 무시됩니다.
- ONLY_SINGLE_UNDERSCORE -> 빈 문자열이 됩니다.
- ONLY_CONTINUOUS_UNDERSCORES -> 빈 문자열이 됩니다.
- 복잡한 컨벤션을 뒤섞은 문자열
  - 앞뒤 공백 -> 무시됩니다.
  - 앞뒤 연속적인 공백 -> 무시됩니다.
  - 연속되는 하이픈, 언더스코어, 공백 문자 -> 단어 사이에 있다면 단일 공백으로 바뀝니다.
  - eXaMplE 등 대소문자가 혼재하는 컨벤션 -> 각 단어의 첫 글자가 대문자, 다른 글자가 소문자로 바뀝니다.