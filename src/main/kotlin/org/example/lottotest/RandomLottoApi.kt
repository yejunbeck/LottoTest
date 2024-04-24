package org.example.lottotest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RandomLottoApi

fun main(args: Array<String>) {
    runApplication<RandomLottoApi>(*args)
}

@RestController
class LottoController {

    // 당첨 번호
    private val winningNumbers = generateWinningNumbers()

    // 랜덤 로또 번호 생성 API
    @GetMapping("/lotto")
    fun generateLottoNumbers(): LottoResult {
        val lottoNumbers = mutableListOf<Int>()
        val random = java.util.Random()

        // 중복 없는 7개의 랜덤 숫자 생성
        while (lottoNumbers.size < 7) {
            val randomNumber = random.nextInt(45) + 1
            if (!lottoNumbers.contains(randomNumber)) {
                lottoNumbers.add(randomNumber)
            }
        }

        // 당첨 번호와 비교하여 당첨 여부 확인
        val matchedNumbers = lottoNumbers.intersect(winningNumbers).toList()
        val isWinner = matchedNumbers.size >= 3

        // 결과 반환
        return LottoResult(lottoNumbers, winningNumbers, matchedNumbers, isWinner)
    }

    // 당첨 번호 생성 메서드
    private fun generateWinningNumbers(): List<Int> {
        val winningNumbers = mutableListOf<Int>()
        val random = java.util.Random()

        while (winningNumbers.size < 7) {
            val randomNumber = random.nextInt(45) + 1
            if (!winningNumbers.contains(randomNumber)) {
                winningNumbers.add(randomNumber)
            }
        }

        return winningNumbers
    }
}

// 로또 결과 클래스
data class LottoResult(
    val userNumbers: List<Int>,          // 사용자의 로또 번호
    val winningNumbers: List<Int>,       // 당첨 번호
    val matchedNumbers: List<Int>,       // 사용자의 번호와 당첨 번호의 일치하는 번호
    val isWinner: Boolean                // 당첨 여부
)
