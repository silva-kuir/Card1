:CARDLAYOUT CODE:

1. Class and Structure
ContactManagerApp: This is the main class that manages the user interface (UI) and functionality for the contact manager.
Contact: A record is defined to store contact details like name, phone number, and email. This is a simple container that provides immutable data for each contact.
2. Instance Variables
frame: This represents the main window of the application.
cardPanel: A JPanel that uses a CardLayout to switch between different views (like list, details, and form).
cardLayout: The layout manager used for switching between views in the cardPanel.
contactListModel: A model that holds the list of contact names.
contactList: A JList that displays the names of contacts.
contacts: An ArrayList that stores the Contact objects.
nameField, phoneField, emailField: These are text fields in the contact form for entering a contact's details.
detailsLabel: A label used to display the selected contact's details.
3. Constructor Logic
frame setup: The main window (JFrame) is created, titled "Card Layout," and sized at 600x600 pixels.
CardLayout: A CardLayout is used to manage different views (the contact list, contact details, and the contact form).
JList and DefaultListModel: These are used to display and manage the contact list by adding names of contacts in contactListModel.
The following methods are called during initialization:
createContactListView(): This creates the contact list view.
createContactDetailsView(): This creates the contact details view.
createContactFormView(): This creates the contact form view for adding new contacts.
After these components are created, the cardPanel (with all three views) is added to the frame and made visible.

4. View Creation Methods
createContactListView(): This method sets up the initial view where the list of contact names is shown. It adds two buttons: "Add New Contact" (which switches to the contact form view) and "View Details" (which shows the details of the selected contact).
createContactDetailsView(): This view displays the details of the selected contact (name, phone, and email). It also has a "Back to List" button that takes the user back to the contact list view.
createContactFormView(): This is the form where users can input a contact's name, phone, and email. There are two buttons: "Save Contact" (to save the new contact) and "Cancel" (to return to the contact list without saving).
5. Action Logic
Add New Contact: When the "Add New Contact" button is clicked, the application switches to the contact form view (cardLayout.show(cardPanel, "ContactForm")).
Save Contact: The saveContact() method validates that the user has filled in all fields. If any field is empty, an error message is shown. If all fields are filled, a new Contact object is created and added to the contacts list and contactListModel. The contact form is reset, and the app switches back to the contact list view.
View Contact Details: When the "View Details" button is clicked, it checks if a contact is selected. If no contact is selected, an error message is shown. If a contact is selected, the details of the contact (name, phone, and email) are displayed in the details label, and the application switches to the contact details view.
Back to List: The "Back to List" button in the contact details view allows the user to return to the contact list view.
6. Main Method
SwingUtilities.invokeLater(): This ensures that the creation of the ContactManagerApp instance runs on the Event Dispatch Thread (EDT), which is the proper way to create GUI components in Swing.
7. Contact Record
The Contact class is a Java record that holds the contact information (name, phone, and email). A record in Java is a special class that automatically generates methods like equals(), hashCode(), and toString() and is a convenient way to define immutable data structures.
