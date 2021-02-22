package com.ingeneo.application.views.crearsala;

import com.ingeneo.application.data.entity.Sala;
import com.ingeneo.application.data.service.SalaService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ingeneo.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.Key;


@Route(value = "CrearSala", layout = MainView.class)
@PageTitle("Crear Sala")
@CssImport("./views/crearsala/crear-sala-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class CrearSalaView extends Div {
    public int i =0;

    private TextField nomsala = new TextField("Nombre Sala");
    private DatePicker dateOfBirth = new DatePicker("Fecha Apertura");
    private formatosalaField formatsala = new formatosalaField("Formato Sala");
    private ciudadField ciudad = new ciudadField("Ciudad");
    private sucursalField nomsuc = new sucursalField("Sucursal");

    private Button btnsala = new Button("CrearSala");
    private Button btnagregarfila = new Button("Agregar Filas");
    private TextField numfila = new TextField("Fila #");
    private TextField numsillas = new TextField("Número de sillas");
    private Binder<Sala> binder = new Binder(Sala.class);


    public CrearSalaView(SalaService personService) {
        VerticalLayout listfilas = new VerticalLayout();
        HorizontalLayout listfilas2 = new HorizontalLayout();
        addClassName("crear-sala-view");
        btnagregarfila.addClickShortcut(Key.ENTER);
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();
        
        btnsala.addClickListener(e -> {
        	if (!nomsala.isEmpty())
               Notification.show( "Sala creada exitosamente");
        	else
        		Notification.show( "Debe agregar datos de la sala");
            clearForm();
        });

        btnagregarfila.addClickListener(e -> {
            Checkbox checkbox1 = new Checkbox(numfila.getValue());
           // Checkbox checkbox2 = new Checkbox(numsillas.getValue());
            // checkbox1 = (char)('A'+i) + "  "+checkbox2;
           String checkbox2 =  (numsillas.getValue());
           listfilas.add((char)('A'+i) + ""+ checkbox2+"   ");
            i++;
            listfilas2.add(checkbox2);
            //personService.update(binder.getBean());
            //Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            //clearForm();

            add(listfilas,
               new HorizontalLayout(numsillas,btnagregarfila,btnsala )
            );

        });
    }

    private void clearForm() {
        binder.setBean(new Sala());
    }

    private Component createTitle() {
        return new H3("Crear Salas");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nomsala, dateOfBirth, formatsala, ciudad, nomsuc,numsillas);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        btnagregarfila.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(btnagregarfila);
        buttonLayout.add(btnsala);
        return buttonLayout;
    }

    //lista de formatos de sala
    private static class formatosalaField extends CustomField<String> {
        private ComboBox<String> formatosal = new ComboBox<>();
        private TextField number = new TextField();

        public formatosalaField(String label) {
            setLabel(label);
            formatosal.setWidth("200px");
            formatosal.setPlaceholder("Formato Sala");
            formatosal.setPattern("\\+\\d*");
            formatosal.setPreventInvalidInput(true);
            formatosal.setItems("2D", "3D", "4D", "DYNAMIX");
            formatosal.addCustomValueSetListener(e -> formatosal.setValue(e.getDetail()));
            number.setPattern("\\d*");
            number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(formatosal);
            layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (formatosal.getValue() != null && number.getValue() != null) {
                String s = formatosal.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                formatosal.clear();
                number.setValue(parts[0]);
            } else if (parts.length == 2) {
                formatosal.setValue(parts[0]);
                number.setValue(parts[1]);
            } else {
                formatosal.clear();
                number.clear();
            }
        }
    }
    //lista de empleados admin
    private static class nomadminField extends CustomField<String> {
        private ComboBox<String> empleadoadmin = new ComboBox<>();
        private TextField number = new TextField();

        public nomadminField(String label) {
            setLabel(label);
            empleadoadmin.setWidth("280px");
            empleadoadmin.setPlaceholder("Listado de empleados administradores");
            empleadoadmin.setPattern("\\+\\d*");
            empleadoadmin.setPreventInvalidInput(true);
            empleadoadmin.setItems("Camilo", "Isa", "Cristina",  "Andrés");
            empleadoadmin.addCustomValueSetListener(e -> empleadoadmin.setValue(e.getDetail()));
            number.setPattern("\\d*");
            number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(empleadoadmin);
            //layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (empleadoadmin.getValue() != null && number.getValue() != null) {
                String s = empleadoadmin.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String nomadmin) {
            String[] parts = nomadmin != null ? nomadmin.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                empleadoadmin.clear();
                //number.setValue(parts[0]);
            } else if (parts.length == 2) {
                empleadoadmin.setValue(parts[0]);
                //number.setValue(parts[1]);
            } else {
                empleadoadmin.clear();
                //number.clear();
            }
        }
    }

    //lista de ciudades
    private static class ciudadField extends CustomField<String> {
        private ComboBox<String> listcity = new ComboBox<>();
        private TextField number = new TextField();

        public ciudadField(String label) {
            setLabel(label);
            listcity.setWidth("220px");
            listcity.setPlaceholder("Listado de ciudades");
            listcity.setPattern("\\+\\d*");
            listcity.setPreventInvalidInput(true);
            listcity.setItems("Medellín", "Bogotá", "Cali", "Cartagena");
            listcity.addCustomValueSetListener(e -> listcity.setValue(e.getDetail()));
            //number.setPattern("\\d*");
            //number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(listcity);
            //layout.setFlexGrow(1.0, number);
            add(layout);
        }

       @Override
       protected String generateModelValue() {
            if (listcity.getValue() != null && number.getValue() != null) {
                String s = listcity.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String listadociudad) {
            String[] parts = listadociudad != null ? listadociudad.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                listcity.clear();
                //number.setValue(parts[0]);
            } else if (parts.length == 2) {
                listcity.setValue(parts[0]);
                //number.setValue(parts[1]);
            } else {
                listcity.clear();
                //number.clear();
            }
        }
    }

    //lista de sucursales
    private static class sucursalField extends CustomField<String> {
        private ComboBox<String> listsuc = new ComboBox<>();
        private TextField number = new TextField();

        public sucursalField(String label) {
            setLabel(label);
            listsuc.setWidth("220px");
            listsuc.setPlaceholder("Listado de sucursales");
            listsuc.setPattern("\\+\\d*");
            listsuc.setPreventInvalidInput(true);
            listsuc.setItems("Oviedo", "Country", "La Flora", "CC Cartagena");
            listsuc.addCustomValueSetListener(e -> listsuc.setValue(e.getDetail()));
            //number.setPattern("\\d*");
            //number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(listsuc);
            //layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        protected String generateModelValue() {
            if (listsuc.getValue() != null && number.getValue() != null) {
                String s = listsuc.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String listadosucursal) {
            String[] parts = listadosucursal != null ? listadosucursal.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                listsuc.clear();
                //number.setValue(parts[0]);
            } else if (parts.length == 2) {
                listsuc.setValue(parts[0]);
                //number.setValue(parts[1]);
            } else {
                listsuc.clear();
                //number.clear();
            }
        }
    }

}
