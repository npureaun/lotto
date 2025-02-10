package com.example.solution.domain.lotto.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Collections.swap
import kotlin.random.Random

@Entity
@Table(name = "LOTTO")
class Lotto(
    @Column
    val number: String,

    @Column
    var rank:Int,

    @Column
    var isUse: Boolean=false,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {

    companion object {

        private fun shuffleLotto(lottoList: MutableList<Lotto>):List<Lotto> {
            // 4등을 1~10000 사이에 배치
            val fourthPlaceIndexes = lottoList.indices.filter { lottoList[it].rank == 4 }
            for (index in fourthPlaceIndexes) {
                val newIndex = Random.nextInt(0, 10000)
                swap(lottoList, index, newIndex)
            }

            // 3등을 1000~8000 사이에 배치
            val thirdPlaceIndexes = lottoList.indices.filter { lottoList[it].rank == 3 }
            for (index in thirdPlaceIndexes) {
                var newIndex:Int
                do {
                    newIndex = Random.nextInt(999, 8000)
                }while (lottoList[newIndex].rank!=0)
                swap(lottoList, index, newIndex)
            }

            // 2등을 2000~7000 사이에 배치
            val secondPlaceIndexes = lottoList.indices.filter { lottoList[it].rank == 2 }
            for (index in secondPlaceIndexes) {
                var newIndex:Int
                do {
                    newIndex =Random.nextInt(1999, 7000)
                }while (lottoList[newIndex].rank!=0)
                swap(lottoList, index, newIndex)
            }

            // 1등을 1에 배치
            val firstPlaceIndexes = lottoList.indexOfFirst { it.rank==1 }
            swap(lottoList,0, firstPlaceIndexes)

            return lottoList
        }

        private fun generateNumber(target: String, matchCount: Int, count: Int):List<String>{
            val result = mutableListOf<String>()
            val targetChars = target.toCharArray()

            repeat(count) {
                val newNumber = targetChars.copyOf()
                val indices = (0..5).shuffled().take(6 - matchCount) // 변경할 인덱스 선정

                for (index in indices) {
                    var newDigit: Char
                    do {
                        newDigit = Random.nextInt(0, 10).toString()[0] // 0~9 랜덤 숫자
                    } while (newDigit == targetChars[index]) // 기존 숫자와 다르게 설정
                    newNumber[index] = newDigit
                }

                result.add(String(newNumber))
            }

            return result
        }

        fun generateLotto(lottoNumber:String):List<Lotto> {
            val lottoList = mutableListOf<Lotto>()
            val mainNumber=lottoNumber

            generateNumber(mainNumber,6,1)
                .forEach { lottoList.add(Lotto(it,1)) }


            generateNumber(mainNumber,5,5)
                .forEach { lottoList.add(Lotto(it,2)) }


            generateNumber(mainNumber,4,44)
                .forEach { lottoList.add(Lotto(it,3)) }


            generateNumber(mainNumber,3,950)
                .forEach { lottoList.add(Lotto(it,4)) }

            generateNumber(mainNumber,0,10000-(950+44+5+1))
                .forEach { lottoList.add(Lotto(it,0)) }

            return shuffleLotto(lottoList)
        }

    }
}
