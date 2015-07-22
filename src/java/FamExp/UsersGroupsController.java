

package FamExp;



import FamExp.util.JsfUtil;
import FamExp.util.JsfUtil.PersistAction;


import famexp.UsersGroupsPK;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@ManagedBean(name = "UsersGroupsController")
@SessionScoped
public class UsersGroupsController implements Serializable {

    @EJB
    private FamExp.UsersGroupsFacade ejbFacade;
    private List<UsersGroups> items = null;
    private UsersGroups selected;

    public UsersGroupsController() {
    }

    public UsersGroups getSelected() {
        return selected;
    }

    public void setSelected(UsersGroups selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
                selected.setUsersGroupsPK(new UsersGroupsPK());
    }

    private UsersGroupsFacade getFacade() {
        return ejbFacade;
    }

    public UsersGroups prepareCreate() {
        selected = new UsersGroups();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsersGroupsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersGroupsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsersGroupsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsersGroups> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<UsersGroups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsersGroups> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsersGroups.class)
    public static class UsersGroupsControllerConverter implements Converter {

        
        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";
        
        
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersGroupsController controller = (UsersGroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "UsersGroupsController");
            return controller.getFacade().find(getKey(value));
        }


        UsersGroupsPK getKey(String value) {
            UsersGroupsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new UsersGroupsPK();
            key.setGroupName(values[0]);
            key.setUserId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(UsersGroupsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupName());
            sb.append(SEPARATOR);
            sb.append(value.getUserId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsersGroups) {
                UsersGroups o = (UsersGroups) object;
                return getStringKey(o.getUsersGroupsPK());


            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsersGroups.class.getName()});
                return null;
            }
        }

    }

}
