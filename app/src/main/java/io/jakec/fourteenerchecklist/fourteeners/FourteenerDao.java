package io.jakec.fourteenerchecklist.fourteeners;

import java.util.List;

public interface FourteenerDao {
    List<Fourteener> getAllFourteeners();
    List<Fourteener> getFourteenersByRange(String range);
    Fourteener getFourteenerById(int id);
}
