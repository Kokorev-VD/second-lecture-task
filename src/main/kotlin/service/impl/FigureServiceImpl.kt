package service.impl

import exception.BadPropertyException
import model.Circle
import model.Figure
import model.Square
import service.FigureService
import kotlin.math.pow

object FigureServiceImpl : FigureService {
    private val figures = mutableListOf<Figure>()

    override fun addSquare(property: Double) {
        when {
            property > 0 -> figures.add(Square(property))
            else -> throw BadPropertyException(property.toString())
        }
    }

    override fun addCircle(property: Double) {
        when {
            property > 0 -> figures.add(Circle(property))
            else -> throw BadPropertyException(property.toString())
        }
    }

    override fun getPerimeter(): List<Double> {
        val result = mutableListOf<Double>()
        for (figure in figures) {
            when (figure) {
                is Square -> result.add(4 * figure.property)
                is Circle -> result.add(2 * Math.PI * figure.property)
            }
        }
        return result
    }

    override fun getArea(): List<Double> {
        val result = mutableListOf<Double>()
        for (figure in figures) {
            when (figure) {
                is Square -> result.add(figure.property * figure.property)
                is Circle -> result.add(Math.PI * figure.property.pow(2))
            }
        }
        return result
    }
}