package service.impl

import enums.Operation
import exception.AppException
import exception.BadPropertyException
import exception.WrongFigureTypeException
import exception.WrongOperationTypeException
import service.ConsoleService

object ConsoleServiceImpl : ConsoleService {

    private fun getOperation(operationType: String) : Operation =
        when(operationType) {
            "1" -> Operation.INSERT
            "2" -> Operation.GET_AREA
            "3" -> Operation.GET_PERIMETER
            "4" -> Operation.EXIT
            else -> throw WrongOperationTypeException(operationType)
        }

    private fun addFigure() {
        println("Введите тип фигуры, которую хотите добавить:\n1) квадрат\n2) круг")
        val figureType = readln()
        when (figureType) {
            "1" -> {
                println("Введите длину стороны квадрата")
                val input = readln()
                val side = try {
                    input.toDouble()
                } catch (e: NumberFormatException) {
                    throw BadPropertyException(input)
                }
                FigureServiceImpl.addSquare(side)
            }
            "2" -> {
                println("Введите радиус кргуа")
                val input = readln()
                val radius = try {
                    input.toDouble()
                } catch (e: NumberFormatException) {
                    throw BadPropertyException(input)
                }
                FigureServiceImpl.addCircle(radius)
            }
            else -> throw WrongFigureTypeException(figureType)
        }
    }

    private fun getArea() {
        println("Площадь всех фигур:")
        for ((index, area) in FigureServiceImpl.getArea().withIndex()) {
            println("Фигура ${index + 1}) $area")
        }
    }

    private fun getPerimeter() {
        println("Периметры всех фигур:")
        for ((index, perimeter) in FigureServiceImpl.getPerimeter().withIndex()) {
           println("Фигура ${index + 1}) $perimeter")
        }
    }

    override fun work() {
        while(true) {
            try {
                println("Введите тип операции, которую хотите исполнить:\n1) добавить фигуру\n2) получить площадь всех фигур\n3) получить периметр всех фигур\n4) завершить выполнение")
                val operation = getOperation(readln())
                when (operation) {
                    Operation.INSERT -> addFigure()
                    Operation.GET_AREA -> getArea()
                    Operation.GET_PERIMETER -> getPerimeter()
                    Operation.EXIT -> break
                }
            } catch (e: AppException) {
                println("Произошла ошибка: ${e.message}")
            } catch (e: Exception) {
                println("Произошла неизвестная ошибка")
            }
        }
    }
}