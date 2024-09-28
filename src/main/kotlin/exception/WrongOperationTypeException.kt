package exception

class WrongOperationTypeException(operationType: String) : AppException("Введен неизвестный тип операции: $operationType")