package FamExp;

import FamExp.util.JsfUtil;
import FamExp.util.JsfUtil.PersistAction;

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
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.*;

@ManagedBean(name = "TransactionsController")
@SessionScoped
public class TransactionsController implements Serializable {

    @EJB   
    private FamExp.TransactionsFacade ejbFacade;
    private List<Transactions> items = null;
    private Transactions selected;
    private List<Transactions> Transactionslist = null;
    private List<Object[]> listbyuser;
    private List<Object[]> listbyc;
    private int Transactions;
    int id;

    
    public TransactionsController() {
    }

    public List<Object[]> getListbyc() {
        listbyc = getFacade().bycat();
        return listbyc;
            }
    
        public int getTransactions() {
        return Transactions;
    }
        
            public void setTransactions(int Transactions) {
        this.Transactions = Transactions;
    }
            
       public void setListbyc(List<Object[]> listbyc) {
        this.listbyc = listbyc;
    }
       
           
    public Transactions getSelected() {
        return selected;
    }

    public void setSelected(Transactions selected) {
        this.selected = selected;
    }

    public List<Object[]> getListbyuser() {
       
       listbyuser = getFacade().byu();
       
       return listbyuser;
    }

    public void setListbyuser(List<Object[]> listbyuser) {
        this.listbyuser = listbyuser;
    }
             
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TransactionsFacade getFacade() {
        return ejbFacade;
    }

    public Transactions prepareCreate() {
        selected = new Transactions();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TransactionsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TransactionsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TransactionsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Transactions> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    
    
    
        public List<Transactions> getTransactionslist() {
         
           FacesContext context = FacesContext.getCurrentInstance();
             ExternalContext ectx = context.getExternalContext();
              HttpServletRequest request = (HttpServletRequest)ectx.getRequest();
              
              HttpSession session = request.getSession(true);
                  //set a string session attribute
              if(session.getAttribute("MySessionVariable")== null){
              System.out.println("nonsession");
               String a = request.getRemoteUser();
            
            User z = (User) getFacade().byname(a);
           
           //System.out.println("hi");
           //System.out.println(z);
           id = z.getUserId();
         
  
              
              }
             else{
                   id = (int) session.getAttribute("MySessionVariable");
                  
                 
          }
             
            Transactionslist = getFacade().byiduser(id);
                 // depense = getFacade().findAll();
          return Transactionslist;  
       
    }
    
  public void setTransactionslist(List<Transactions> Transactionslist) {
      Transactionslist = this.Transactionslist;
     
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

    public List<Transactions> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Transactions> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Transactions.class)
    public static class TransactionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TransactionsController controller = (TransactionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "TransactionsController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Transactions) {
                Transactions o = (Transactions) object;
                return getStringKey(o.getTransactID());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Transactions.class.getName()});
                return null;
            }
        }

    }

}
