package com.example.util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.*
import io.kotest.matchers.*
import io.kotest.matchers.string.*

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

        "Input _SURROUNDED_WITH_UNDERSCORES_" - {
            val testString = "_EXAMPLE_STRING_"
            val expected = "Example String"
            val result = CapitalizingUtil.capitalize(testString)

            "처음과 끝의 언더스코어는 무시된다." {
                println("$result|")
                println("$expected|")
                println(result.equals(expected))
                result shouldBeEqual expected
            }
        }

        "Input CONTINUOUS__UNDERSCORES___INCLUDED" - {
            val testString = "EXAMPLE__STRING"
            val result = CapitalizingUtil.capitalize(testString)

            "중간에 오는 연속 언더스코어는 단일 공백으로 바뀐다." {
                result shouldBeEqual "Example String"
            }
        }

        "Input CONTINUOUS__UNDERSCORES___TAIL_______" - {
            val testString = "EXAMPLE__STRING_______"
            val result = CapitalizingUtil.capitalize(testString)

            "마지막에 오는 연속 언더스코어는 무시된다." {
                result shouldBeEqual "Example String"
            }
        }

        "Input CONTINUOUS__UNDERSCORES___HEAD_______" - {
            val testString = "_______EXAMPLE__STRING"
            val result = CapitalizingUtil.capitalize(testString)

            "처음에 오는 연속 언더스코어는 무시된다." {
                result shouldBeEqual "Example String"
            }
        }

        "INPUT ONLY_SINGLE_UNDERSCORE" - {
            val testString = "_"
            val result = CapitalizingUtil.capitalize(testString)

            "빈 문자열이 된다." {
                result shouldBeEqual ""
            }
        }

        "INPUT ONLY_CONTINUOUS_UNDERSCORES" - {
            val testString = "___"
            val result = CapitalizingUtil.capitalize(testString)

            "빈 문자열이 된다." {
                result shouldBeEqual ""
            }
        }

        "INPUT Multiple blanks and mixed convention" - {
            val testString = "   ---   ___   _-_   eXaMplE---   ___   ---   stRINg   ---   ___   -_-_-___   "
            val result = CapitalizingUtil.capitalize(testString)

            "연속적인 공백, 언더스코어, 하이픈은 모두 단일 공백으로 되며, 앞뒤에 오면 모두 생략된다." {
                result shouldBeEqual "Example String"
            }
        }
    }
})