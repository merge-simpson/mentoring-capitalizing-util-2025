package com.example.util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

// 상속 불가: class (kotlin) -> public final class (java)
// 상속 허용: open class (kotlin) -> public class (java)
// 상속 부분 허용: sealed 클래스 (자바에서도 sealed)
class CapitalizingUtilTest : FreeSpec({
    // BDD (행위 기반 표현) -> 서비스 만들 때
    // 유틸 함수 -> 함수 이름으로 해도 되지 않을까? (상황에 따라)
    "capitalize(str): String" - {
        "Input UPPER_CASE_WITH_UNDERSCORES" - {
            val testString = "EXAMPLE_STRING"
            val result = CapitalizingUtil.capitalize(testString)

            "첫 글자는 대문자여야 한다." {
                result shouldStartWith "E"
            }

            "두 번째 글자는 소문자여야 한다." {
                result[1] shouldBe 'x'
            }

            "언더스코어가 있던 위치는 모두 띄어쓰기로 바뀐다." {
                // testString.indices.forEach { ... }
                (0..<testString.length).forEach {
                    if (testString[it] == '_') {
                        result[it] shouldBe ' '
                    }
                }
            }

            "원래는 이거 하나만 하면 됩니다." {
                result shouldBeEqual "Example String" // assertion
            }
        }

        "Input _STARTING_WITH_ONE_UNDERSCORE" - {
            "..." {
                // ...
            }
        }
    }
})