## TODO

- [] Handle going back in the verification code screen
- [] Handle autofocus on text fields
- [] Add Splash screen
- [] Error Handling
- [] Handle register with previous email
- [] Replace SharedPreference with PreferenceDataStore
- [] Replace PreferenceDataStore with ProtoDataStore
- [] Room
- [] Navigation testing
- [] Create model for UI state similar to LoginFormState in d5af9968

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