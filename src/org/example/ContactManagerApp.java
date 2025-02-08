package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//-------Creating the contact manager application--------
public class ContactManagerApp {

    //------Instances of the application--------------
    private final JFrame frame;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final DefaultListModel<String> contactListModel;
    private final JList<String> contactList;
    private final ArrayList<org.example.Contact> contacts = new ArrayList<>();
    private JTextField nameField, phoneField, emailField;
    private JLabel detailsLabel;

    //---------Initializing the constructor for the ContactManagerApp------------
    public ContactManagerApp() {

        //------Creating main frame--------------
        frame = new JFrame("Card Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //-------Setting the size of the frame----------
        frame.setSize(600, 600);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);

        createContactListView();
        createContactDetailsView();
        createContactFormView();

        //-----Adding cardPanel to the frame-----------
        frame.add(cardPanel);

        //-----Setting visibility parameter of the frame--------
        frame.setVisible(true);
    }

    private void createContactListView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(contactList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add New Contact");
        JButton viewButton = new JButton("View Details");

        addButton.addActionListener(e -> cardLayout.show(cardPanel, "ContactForm"));
        viewButton.addActionListener(e -> showContactDetails());

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        cardPanel.add(panel, "ContactList");
    }

    public void createContactDetailsView() {
        JPanel panel = new JPanel(new BorderLayout());
        detailsLabel = new JLabel("Select a contact from the list.", SwingConstants.CENTER);
        panel.add(detailsLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Back to List");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ContactList"));
        panel.add(backButton, BorderLayout.SOUTH);

        cardPanel.add(panel, "ContactDetails");
    }

    private void createContactFormView() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton saveButton = new JButton("Save Contact");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> saveContact());
        cancelButton.addActionListener(e -> cardLayout.show(cardPanel, "ContactList"));

        panel.add(saveButton);
        panel.add(cancelButton);
        cardPanel.add(panel, "ContactForm");
    }

    private void saveContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        org.example.Contact contact = new org.example.Contact(name, phone, email);
        contacts.add(contact);
        contactListModel.addElement(name);

        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");

        cardLayout.show(cardPanel, "ContactList");
    }

    private void showContactDetails() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a contact.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        org.example.Contact contact = contacts.get(selectedIndex);
        detailsLabel.setText("Name: " + contact.name() +  " "+  "Phone: " +   contact.phone()    + " "+   ("Email: "   + contact.email() ));
        cardLayout.show(cardPanel, "ContactDetails");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManagerApp::new);
    }

}


record Contact(String name, String phone, String email){


        }