package myway.group.uzblogs.api

data class BaseResponse<T>( //Template ko'rinishda yaratdik
    val data:T // T bu list, massiv object class bo'lishi mumkin  keladdigan responsega qarab o'zgartiramiza
)