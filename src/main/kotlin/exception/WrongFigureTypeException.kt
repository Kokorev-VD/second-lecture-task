package exception

class WrongFigureTypeException(figureType: String) : AppException("Неизвестный тип фигуры $figureType")