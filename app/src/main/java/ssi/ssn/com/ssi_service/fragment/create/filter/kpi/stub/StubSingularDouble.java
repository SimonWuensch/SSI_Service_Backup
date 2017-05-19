package ssi.ssn.com.ssi_service.fragment.create.filter.kpi.stub;

import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ssi.ssn.com.ssi_service.R;
import ssi.ssn.com.ssi_service.fragment.create.filter.kpi.view.VerificationButton;
import ssi.ssn.com.ssi_service.model.data.source.filter.kpi.FilterKpi;
import ssi.ssn.com.ssi_service.model.data.source.filter.kpi.KpiTypeSingularDouble;
import ssi.ssn.com.ssi_service.model.data.source.filter.kpi.VerificationObject;
import ssi.ssn.com.ssi_service.model.network.response.kpi.definitions.ResponseKpiDefinition;

public class StubSingularDouble extends AbstractKpiTypeStub{

    private static int STUB_LAYOUT = R.layout.fragment_create_filter_kpi_stub_singular_double;

    private KpiTypeSingularDouble kpiType;
    private EditText etValue;
    private VerificationButton vbValue;

    private StubSingularDouble(FilterKpi filter, ViewStub viewStub){
        super(filter, viewStub);
        this.kpiType = (KpiTypeSingularDouble) filter.getKpiType();
        initViewComponents();
        fillViewWithFilterInfo();
    }

    private StubSingularDouble(ResponseKpiDefinition definition, ViewStub viewStub){
        super(definition, viewStub);
        initViewComponents();
    }

    public static StubSingularDouble initStub(FilterKpi filter, ViewStub viewStub) {
        return new StubSingularDouble(filter, viewStub);
    }

    public static StubSingularDouble initStub(ResponseKpiDefinition definition, ViewStub viewStub) {
        return new StubSingularDouble(definition, viewStub);
    }

    @Override
    public void initViewComponents(){
        viewStub.setLayoutResource(STUB_LAYOUT);
        View inflated = viewStub.inflate();

        etValue = (EditText) inflated.findViewById(R.id.fragment_create_kpi_filter_stub_singular_double_edit_text_value);
        vbValue = (VerificationButton) inflated.findViewById(R.id.fragment_create_kpi_filter_stub_singular_double_button_verification_object_value);
    }

    @Override
    protected void fillViewWithFilterInfo(){
        etValue.setText(kpiType.getVoValue().equals(VerificationObject.IGNORE) ? "" : String.valueOf(kpiType.getValue()));
        vbValue.setVerificationObject(kpiType.getVoValue());
    }

    @Override
    public FilterKpi.KpiTypeSignification getKpiTypeSignification(){
        return FilterKpi.KpiTypeSignification.SINGULAR_DOUBLE;
    }

    @Override
    protected KpiTypeSingularDouble getKpiType(){
        KpiTypeSingularDouble kpiType = new KpiTypeSingularDouble();
        String value = etValue.getText().toString();
        kpiType.setValue(value.isEmpty() ? 0.0 : Double.valueOf(etValue.getText().toString()));
        kpiType.setVoValue(value.isEmpty() ? VerificationObject.IGNORE : getVerificationObjectByIcon((String) vbValue.getText()));
        return kpiType;
    }

    @Override
    public boolean isReadyForCreation() {
        if (!etValue.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public List<EditText> getAllEditTextViews(){
        return new ArrayList<EditText>(){
            {
                add(etValue);
            }
        };
    }

    @Override
    public List<VerificationButton> getAllVerificationButtonViews(){
        return new ArrayList<VerificationButton>(){
            {
                add(vbValue);
            }
        };
    }
}
