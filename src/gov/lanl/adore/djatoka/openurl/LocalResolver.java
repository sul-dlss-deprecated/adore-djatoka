
package gov.lanl.adore.djatoka.openurl;

import gov.lanl.adore.djatoka.util.ImageRecord;
import info.openurl.oom.entities.Referent;
import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 * A referent resolver that expects all image uris to be file:// and all images to be local. It skips the image migration step, so you arent copying from local disk to cache, because that is a waste of time
 * @author jdeering
 */
public class LocalResolver implements IReferentResolver{

    @Override
    public ImageRecord getImageRecord(String rftId) throws ResolverException {
        //I was getting newlines in the uris for an unknown reason, they get removed here. 
        String id=rftId.replace("\n","");
        String rft=id.replace("file://", "");
        return new ImageRecord(id,rft);
    }

    @Override
    public ImageRecord getImageRecord(Referent rft) {
        //I was getting newlines in the uris for an unknown reason, they get removed here.
        String ref=rft.toString().replace("\n", "");
        return new ImageRecord(ref,ref.replace("file://", ""));
    }

    @Override
    public void setProperties(Properties props) throws ResolverException {
        
    }

    @Override
    public int getStatus(String rftId) {
        //I hope this speeds up head requests significantly
       File target=new File(rftId.replace("file://", ""));
       if (target.exists())
           return HttpServletResponse.SC_OK;
       else
           return HttpServletResponse.SC_NOT_FOUND;
                   
    }

    @Override
    public IReferentMigrator getReferentMigrator() {
        return null;
    }
    
}
