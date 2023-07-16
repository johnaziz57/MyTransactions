## Description

This app is a demo to display dummy transactions to the user

Accessing those transactions must happen either after Registration or Login

First time users to the app must register first, later on the user can login

https://github.com/johnaziz57/MyTransactions/assets/14923445/f366e9dc-cb7a-48fa-829c-543a195a2587


### Registration

1. Open the app
2. Wait for the loading bar
3. Click register
4. Enter your email
5. To verify the email, you must enter the code that was "sent" to your email. Since this is a dummy
   process, just insert any 4 digits code with `0` in it
6. Click confirm
7. Insert a password of your choice twice. Password must contain digits and letters
8. Click confirm
9. Congratulations you are now registered
10. The transaction screen will open up, where you will see a list of dummy 100 transactions

### Logging out

1. Open the transactions screen
2. Click on the top right corner log out icon
3. The screen will close and you will be greeted with the welcome screen again

### Logging in

1. Open the app
2. Wait for the loading bar
3. Click sign in
4. Insert your email and your password
5. Click sign in
6. You are now in the transactions screen

## App Structure

This project follows Google's [recommendation](https://developer.android.com/topic/architecture)
regarding the structure.
![Diagram of Typical app architecture](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)
It has three main components:

1. Data
2. Domain
3. UI

This project is using the following:

1. MVVM
2. Hilt (Dependency Injection)
3. Navigation component
4. Argon2 for hashing password (Winner of 2015 Password
   Hashing [Competition](https://www.password-hashing.net/))
5. JUnit (Testing)
6. Mockito (Mocks for testing)
7. Crypto (Encrypting SharedPreferences)

## Decisions

I have tried to make the app as clean as possible.
I have also ventured in a lot of unfamiliar areas for me, so I could be rusty in making those
decisions.

### Hashing vs Encryption

At the beginning I tried to encrypt the passwords that I save, but it turns out this is not the
recommended way for saving passwords.
I used `CryptoManager` to encrypt and decrypt the data.
Later, I discovered that hashing is the way to go as it is one way function and not reversible.
I saw two options BCrypt and Argon2 (There is more). I found out later Argon2 is the recommended way
to go.

### Encryption

For Argon2, there are certain parameters that can be tweaked to get the hashed value in a fast
manner. I didn't have time to investigate what the best values are, so I used the default ones.

### Saving the data

I decided to save the following data

1. list of registered users and their passwords. This list will be used in the login to verify if
   the email and password are correct.
2. Current user. This value will indicate whether the user is already logged in or not.

I wanted to be able to save the data in somewhere encrypted.
There is ProtoDataStore (currently recommended option from Google) and EncryptedSharedPreferences.
I chose EncryptedSharedPreferences because it was easier and I was a bit tight regarding time.

### Granularity of the domain layer

I tried to split the domain to multiple classes as much as possible.
I tried to make each class has one responsibility.

I also did something new for myself, I tried to have each interactor package contain all its own
data
and its own exceptions. This way it is clearer when an exception is thrown to easily map it an
explainable error message.

### Reactivity

I was planning on adding RxJava to the project, but due to the limited time, I focused more on the
structure of the app the test cases.

### UI

Due to the time limit, I opted to go for very simple UI.
I realised that there can be more to be done like, toolbars, animations ... etc.

## Bugs and shortcomings

The app is not bug free, but once again due to the time limit, I really couldn't follow up on them
all.
The list of known bugs is:

1. Trying to go back in the verification code screen to email submission screen doesn't work.
2. Keyboard doesn't show up automatically when a text field is focused.
3. Animation between activities could be better.

## TODO

- [ ] Handle going back in the verification code screen
- [ ] Handle autofocus on text fields
- [ ] Replace SharedPreference with ProtoDataStore
- [ ] Navigation testing

## Done

- [x] Testing domain
- [x] Testing viewModels
- [x] Entry fragment
- [x] Navigation
- [x] Encryption
- [x] Nested Navigation
- [x] Replace BCrypt with Argon2Kt https://github.com/lambdapioneer/argon2kt based on
  https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html#argon2id or
  https://github.com/phxql/argon2-jvm
- [x] Check best practices for either creating a cipher every time or reuse the old one
- [x] Login
- [x] Improve UI
- [x] Unify Result class
- [x] Error Handling
- [x] Create model for UI state similar to LoginFormState in d5af9968
- [x] ~Add Splash screen~ Added progress bar instead
- [x] Handle register with previous email
