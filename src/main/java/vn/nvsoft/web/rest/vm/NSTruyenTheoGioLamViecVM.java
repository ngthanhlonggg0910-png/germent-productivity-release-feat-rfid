package vn.nvsoft.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSTruyenTheoGioLamViecVM<T> {

    private String gioLam;
    private T value;

}
