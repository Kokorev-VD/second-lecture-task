package exception

class BadPropertyException(property: String) : AppException("Введено неверное значение параметра property: $property")