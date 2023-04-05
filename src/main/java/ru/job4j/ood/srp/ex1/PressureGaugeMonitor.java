package ru.job4j.ood.srp.ex1;

import java.util.List;

public class PressureGaugeMonitor implements PressureGaugeReader {

    @Override
    public double readPressure(int address) {
        return address;
    }

        /*
    Метод scan() является нарушением принципа srp, т.к. основной функционал,
    который должен предоставлять интерфейс - чтение показаний давления; обнаружение
    доступных датчиков на линии - совершенно другая задача
     */

    @Override
    public List<Integer> scan() {
        return null;
    }
}
