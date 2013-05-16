package dummy;

import EuroSys.common.validators.AbstractValidator;
import EuroSys.octave.exception.NotValidatedException;
import EuroSys.parser.NameParser.RgFolderNameParser;
import EuroSys.parser.reportParser.ReportParser;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyValidator extends AbstractValidator {

   private RgFolderNameParser rg;
   private ReportParser rp;

   protected void __validate(RgFolderNameParser rg, ReportParser rp) throws NotValidatedException {
   }


}
