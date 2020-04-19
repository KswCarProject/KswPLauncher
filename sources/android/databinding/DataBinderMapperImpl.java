package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
    DataBinderMapperImpl() {
        addMapper((DataBinderMapper) new com.wits.ksw.DataBinderMapperImpl());
    }
}
