package ru.job4j.ood.srp.ex1;

import java.util.List;

public interface PressureGaugeReader {

    /*
    Метод readPressure() должен учитывать, например, что для разных моделей датчиков
    будут разные протоколы общения. Поэтому правильно
    выделить ещё одну абстракцию PressureGaugeModel в соответствии с принципом SRP
    */
    double readPressure(int address);

    /*
    Метод scan() является нарушением принципа SRP, т.к. основной функционал,
    который должен предоставлять интерфейс - чтение показаний давления; обнаружение
    доступных датчиков на линии - совершенно другая задача. Правильно будет выделить
    в абстракцию PressureGaugeIdentifier
     */
    List<Integer> scan();
}
