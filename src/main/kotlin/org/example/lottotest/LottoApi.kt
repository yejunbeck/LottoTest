package org.example.lottotest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class LottoApiApplication

fun main(args: Array<String>) {
    runApplication<LottoApiApplication>(*args)
}

@RestController
class LottoController {

    // 랜덤 로또 번호 생성 API
    @GetMapping("/lotto")
    fun generateLottoNumbers(): List<Int> {
        val lottoNumbers = mutableListOf<Int>()
        val random = java.util.Random()

        // 중복 없는 7개의 랜덤 숫자 생성
        while (lottoNumbers.size < 7) {
            val randomNumber = random.nextInt(45) + 1
            if (!lottoNumbers.contains(randomNumber)) {
                lottoNumbers.add(randomNumber)
            }
        }

        // 생성된 로또 번호 반환
        return lottoNumbers
    }
}