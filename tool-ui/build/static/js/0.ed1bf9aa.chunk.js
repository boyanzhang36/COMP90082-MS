(this.webpackJsonprestool=this.webpackJsonprestool||[]).push([[0],{134:function(e,n,t){"use strict";var r=t(86);t(29);n.a=r.a},258:function(e,n,t){"use strict";var r=t(38),a=new r.a("I18n"),o=function(){function e(e){this._options=null,this._lang=null,this._dict={},this._options=Object.assign({},e),this._lang=this._options.language,!this._lang&&"undefined"!==typeof window&&window&&window.navigator&&(this._lang=window.navigator.language),a.debug(this._lang)}return e.prototype.setLanguage=function(e){this._lang=e},e.prototype.get=function(e,n){if(void 0===n&&(n=void 0),!this._lang)return"undefined"!==typeof n?n:e;var t=this._lang,r=this.getByLanguage(e,t);return r||(t.indexOf("-")>0&&(r=this.getByLanguage(e,t.split("-")[0])),r||("undefined"!==typeof n?n:e))},e.prototype.getByLanguage=function(e,n,t){if(void 0===t&&(t=null),!n)return t;var r=this._dict[n];return r?r[e]:t},e.prototype.putVocabulariesForLanguage=function(e,n){var t=this._dict[e];t||(t=this._dict[e]={}),Object.assign(t,n)},e.prototype.putVocabularies=function(e){var n=this;Object.keys(e).map((function(t){n.putVocabulariesForLanguage(t,e[t])}))},e}(),i=t(25);t.d(n,"a",(function(){return s}));var u=new r.a("I18n"),E=null,c=null,s=function(){function e(){}return e.configure=function(n){return u.debug("configure I18n"),n?(E=Object.assign({},E,n.I18n||n),e.createInstance(),E):E},e.getModuleName=function(){return"I18n"},e.createInstance=function(){u.debug("create I18n instance"),c||(c=new o(E))},e.setLanguage=function(n){return e.checkConfig(),c.setLanguage(n)},e.get=function(n,t){return e.checkConfig()?c.get(n,t):"undefined"===typeof t?n:t},e.putVocabulariesForLanguage=function(n,t){return e.checkConfig(),c.putVocabulariesForLanguage(n,t)},e.putVocabularies=function(n){return e.checkConfig(),c.putVocabularies(n)},e.checkConfig=function(){return c||(c=new o(E)),!0},e}();i.a.register(s)},319:function(e,n,t){"use strict";t.d(n,"a",(function(){return C})),t.d(n,"b",(function(){return E})),t.d(n,"c",(function(){return a})),t.d(n,"d",(function(){return d})),t.d(n,"e",(function(){return f})),t.d(n,"f",(function(){return _})),t.d(n,"g",(function(){return g})),t.d(n,"h",(function(){return p})),t.d(n,"i",(function(){return h})),t.d(n,"j",(function(){return I})),t.d(n,"k",(function(){return i})),t.d(n,"l",(function(){return l})),t.d(n,"m",(function(){return L})),t.d(n,"n",(function(){return A})),t.d(n,"o",(function(){return T})),t.d(n,"p",(function(){return O})),t.d(n,"q",(function(){return c})),t.d(n,"r",(function(){return S})),t.d(n,"s",(function(){return s})),t.d(n,"t",(function(){return o})),t.d(n,"u",(function(){return u})),t.d(n,"v",(function(){return r}));var r="username",a="email",o="code",i="phone",u="password",E="country-dial-code-select",c="+1",s="amplify-auth-source",_="amplify-redirected-from-hosted-ui",l="amplify-authenticator-authState",f="Phone number can not be empty",d="No Auth module found, please ensure @aws-amplify/auth is imported",A="No Storage module found, please ensure @aws-amplify/storage is imported",L="No Interactions module found, please ensure @aws-amplify/interactions is imported",g="SETUP_TOTP",T="User has not set up software token mfa",O="User has not verified software token mfa",S="SUCCESS",C="auth",h="UI Auth",p="ToastAuthError",I="AuthStateChange"},320:function(e,n,t){"use strict";t.d(n,"a",(function(){return i}));var r,a,o=t(18);!function(e){e.BACK_TO_SIGN_IN="Back to Sign In",e.CHANGE_PASSWORD_ACTION="Change",e.CHANGE_PASSWORD="Change Password",e.CODE_LABEL="Verification code",e.CODE_PLACEHOLDER="Enter code",e.CONFIRM_SIGN_UP_CODE_LABEL="Confirmation Code",e.CONFIRM_SIGN_UP_CODE_PLACEHOLDER="Enter your code",e.CONFIRM_SIGN_UP_HEADER_TEXT="Confirm Sign up",e.CONFIRM_SIGN_UP_LOST_CODE="Lost your code?",e.CONFIRM_SIGN_UP_RESEND_CODE="Resend Code",e.CONFIRM_SIGN_UP_SUBMIT_BUTTON_TEXT="Confirm",e.CONFIRM_SMS_CODE="Confirm SMS Code",e.CONFIRM_TOTP_CODE="Confirm TOTP Code",e.CONFIRM="Confirm",e.CREATE_ACCOUNT_TEXT="Create account",e.EMAIL_LABEL="Email Address *",e.EMAIL_PLACEHOLDER="Enter your email address",e.FORGOT_PASSWORD_TEXT="Forgot your password?",e.LESS_THAN_TWO_MFA_VALUES_MESSAGE="Less than two mfa types available",e.NEW_PASSWORD_LABEL="New password",e.NEW_PASSWORD_PLACEHOLDER="Enter your new password",e.NO_ACCOUNT_TEXT="No account?",e.PASSWORD_LABEL="Password *",e.PASSWORD_PLACEHOLDER="Enter your password",e.PHONE_LABEL="Phone Number *",e.PHONE_PLACEHOLDER="(555) 555-1212",e.QR_CODE_ALT="qrcode",e.RESET_PASSWORD_TEXT="Reset password",e.RESET_YOUR_PASSWORD="Reset your password",e.SELECT_MFA_TYPE_HEADER_TEXT="Select MFA Type",e.SELECT_MFA_TYPE_SUBMIT_BUTTON_TEXT="Verify",e.SEND_CODE="Send Code",e.SUBMIT="Submit",e.SETUP_TOTP_REQUIRED="TOTP needs to be configured",e.SIGN_IN_ACTION="Sign In",e.SIGN_IN_HEADER_TEXT="Sign in to your account",e.SIGN_IN_TEXT="Sign in",e.SIGN_IN_WITH_AMAZON="Sign in with Amazon",e.SIGN_IN_WITH_AUTH0="Sign in with Auth0",e.SIGN_IN_WITH_AWS="Sign in with AWS",e.SIGN_IN_WITH_FACEBOOK="Sign in with Facebook",e.SIGN_IN_WITH_GOOGLE="Sign in with Google",e.SIGN_OUT="Sign Out",e.SIGN_UP_EMAIL_PLACEHOLDER="Email",e.SIGN_UP_HAVE_ACCOUNT_TEXT="Have an account?",e.SIGN_UP_HEADER_TEXT="Create a new account",e.SIGN_UP_PASSWORD_PLACEHOLDER="Password",e.SIGN_UP_SUBMIT_BUTTON_TEXT="Create Account",e.SIGN_UP_USERNAME_PLACEHOLDER="Username",e.SUCCESS_MFA_TYPE="Success! Your MFA Type is now:",e.TOTP_HEADER_TEXT="Scan then enter verification code",e.TOTP_LABEL="Enter Security Code:",e.TOTP_ISSUER="AWSCognito",e.TOTP_SETUP_FAILURE="TOTP Setup has failed",e.TOTP_SUBMIT_BUTTON_TEXT="Verify Security Token",e.TOTP_SUCCESS_MESSAGE="Setup TOTP successfully!",e.UNABLE_TO_SETUP_MFA_AT_THIS_TIME="Failed! Unable to configure MFA at this time",e.USERNAME_LABEL="Username *",e.USERNAME_PLACEHOLDER="Enter your username",e.VERIFY_CONTACT_EMAIL_LABEL="Email",e.VERIFY_CONTACT_HEADER_TEXT="Account recovery requires verified contact information",e.VERIFY_CONTACT_PHONE_LABEL="Phone Number",e.VERIFY_CONTACT_SUBMIT_LABEL="Submit",e.VERIFY_CONTACT_VERIFY_LABEL="Verify",e.ADDRESS_LABEL="Address",e.ADDRESS_PLACEHOLDER="Enter your address",e.NICKNAME_LABEL="Nickname",e.NICKNAME_PLACEHOLDER="Enter your nickname",e.BIRTHDATE_LABEL="Birthday",e.BIRTHDATE_PLACEHOLDER="Enter your birthday",e.PICTURE_LABEL="Picture URL",e.PICTURE_PLACEHOLDER="Enter your picture URL",e.FAMILY_NAME_LABEL="Family Name",e.FAMILY_NAME_PLACEHOLDER="Enter your family name",e.PREFERRED_USERNAME_LABEL="Preferred Username",e.PREFERRED_USERNAME_PLACEHOLDER="Enter your preferred username",e.GENDER_LABEL="Gender",e.GENDER_PLACEHOLDER="Enter your gender",e.PROFILE_LABEL="Profile URL",e.PROFILE_PLACEHOLDER="Enter your profile URL",e.GIVEN_NAME_LABEL="First Name",e.GIVEN_NAME_PLACEHOLDER="Enter your first name",e.ZONEINFO_LABEL="Time zone",e.ZONEINFO_PLACEHOLDER="Enter your time zone",e.LOCALE_LABEL="Locale",e.LOCALE_PLACEHOLDER="Enter your locale",e.UPDATED_AT_LABEL="Updated At",e.UPDATED_AT_PLACEHOLDER="Enter the time the information was last updated",e.MIDDLE_NAME_LABEL="Middle Name",e.MIDDLE_NAME_PLACEHOLDER="Enter your middle name",e.WEBSITE_LABEL="Website",e.WEBSITE_PLACEHOLDER="Enter your website",e.NAME_LABEL="Full Name",e.NAME_PLACEHOLDER="Enter your full name",e.PHOTO_PICKER_TITLE="Picker Title",e.PHOTO_PICKER_HINT="Ancilliary text or content may occupy this space here",e.PHOTO_PICKER_PLACEHOLDER_HINT="Placeholder hint",e.PHOTO_PICKER_BUTTON_TEXT="Button",e.IMAGE_PICKER_TITLE="Add Profile Photo",e.IMAGE_PICKER_HINT="Preview the image before upload",e.IMAGE_PICKER_PLACEHOLDER_HINT="Tap to image select",e.IMAGE_PICKER_BUTTON_TEXT="Upload",e.PICKER_TEXT="Pick a file",e.TEXT_FALLBACK_CONTENT="Fallback Content",e.CONFIRM_SIGN_UP_FAILED="Confirm Sign Up Failed",e.SIGN_UP_FAILED="Sign Up Failed"}(r||(r={})),function(e){e.CHATBOT_TITLE="ChatBot Lex",e.TEXT_INPUT_PLACEHOLDER="Write a message",e.VOICE_INPUT_PLACEHOLDER="Click mic to speak",e.CHAT_DISABLED_ERROR="Error: Either voice or text must be enabled for the chatbot",e.NO_BOT_NAME_ERROR="Error: Bot name must be provided to ChatBot"}(a||(a={}));var i=Object.assign(Object.assign(Object.assign({},r),o.a),a)},321:function(e,n,t){"use strict";var r,a,o,i,u;t.d(n,"a",(function(){return r})),t.d(n,"b",(function(){return o})),t.d(n,"c",(function(){return a})),t.d(n,"d",(function(){return u})),t.d(n,"e",(function(){return i})),function(e){e.SignUp="signup",e.SignOut="signout",e.SignIn="signin",e.Loading="loading",e.SignedOut="signedout",e.SignedIn="signedin",e.SigningUp="signingup",e.ConfirmSignUp="confirmSignUp",e.confirmingSignUpCustomFlow="confirmsignupcustomflow",e.ConfirmSignIn="confirmSignIn",e.confirmingSignInCustomFlow="confirmingsignincustomflow",e.VerifyingAttributes="verifyingattributes",e.ForgotPassword="forgotpassword",e.ResetPassword="resettingpassword",e.SettingMFA="settingMFA",e.TOTPSetup="TOTPSetup",e.CustomConfirmSignIn="customConfirmSignIn",e.VerifyContact="verifyContact"}(r||(r={})),function(e){e.TOTP="TOTP",e.SMS="SMS",e.NOMFA="NOMFA"}(a||(a={})),function(e){e.SoftwareTokenMFA="SOFTWARE_TOKEN_MFA",e.SMSMFA="SMS_MFA",e.NewPasswordRequired="NEW_PASSWORD_REQUIRED",e.MFASetup="MFA_SETUP",e.CustomChallenge="CUSTOM_CHALLENGE"}(o||(o={})),function(e){e.Password="password"}(i||(i={})),function(e){e.username="username",e.email="email",e.phone_number="phone_number"}(u||(u={}))},322:function(e,n,t){"use strict";t.d(n,"a",(function(){return d})),t.d(n,"b",(function(){return L})),t.d(n,"c",(function(){return g})),t.d(n,"d",(function(){return A})),t.d(n,"e",(function(){return f})),t.d(n,"f",(function(){return C})),t.d(n,"g",(function(){return O})),t.d(n,"h",(function(){return T})),t.d(n,"i",(function(){return S}));var r=t(38),a=t(222),o=t(258),i=t(321),u=t(134),E=t(320),c=t(319),s=function(e,n,t,r){return new(t||(t=Promise))((function(a,o){function i(e){try{E(r.next(e))}catch(n){o(n)}}function u(e){try{E(r.throw(e))}catch(n){o(n)}}function E(e){var n;e.done?a(e.value):(n=e.value,n instanceof t?n:new t((function(e){e(n)}))).then(i,u)}E((r=r.apply(e,n||[])).next())}))},_=function(e,n){var t,r,a,o,i={label:0,sent:function(){if(1&a[0])throw a[1];return a[1]},trys:[],ops:[]};return o={next:u(0),throw:u(1),return:u(2)},"function"===typeof Symbol&&(o[Symbol.iterator]=function(){return this}),o;function u(o){return function(u){return function(o){if(t)throw new TypeError("Generator is already executing.");for(;i;)try{if(t=1,r&&(a=2&o[0]?r.return:o[0]?r.throw||((a=r.return)&&a.call(r),0):r.next)&&!(a=a.call(r,o[1])).done)return a;switch(r=0,a&&(o=[2&o[0],a.value]),o[0]){case 0:case 1:a=o;break;case 4:return i.label++,{value:o[1],done:!1};case 5:i.label++,r=o[1],o=[0];continue;case 7:o=i.ops.pop(),i.trys.pop();continue;default:if(!(a=(a=i.trys).length>0&&a[a.length-1])&&(6===o[0]||2===o[0])){i=0;continue}if(3===o[0]&&(!a||o[1]>a[0]&&o[1]<a[3])){i.label=o[1];break}if(6===o[0]&&i.label<a[1]){i.label=a[1],a=o;break}if(a&&i.label<a[2]){i.label=a[2],i.ops.push(o);break}a[2]&&i.ops.pop(),i.trys.pop();continue}o=n.call(e,i)}catch(u){o=[6,u],r=0}finally{t=a=0}if(5&o[0])throw o[1];return{value:o[0]?o[1]:void 0,done:!0}}([o,u])}}},l=new r.a("helpers"),f=function(e){return!!e.shadowRoot&&!!e.attachShadow},d=function(e){a.a.dispatch(c.i,{event:c.h,message:o.a.get(e.message)})},A=function(e,n){a.a.dispatch(c.i,{event:c.j,message:e,data:n})},L=function(e){if(!e.phoneNumberValue)throw new Error(c.e);var n=e.phoneNumberValue.replace(/[-()\s]/g,"");return""+e.countryDialCodeValue+n},g=function(e){if(!(e in i.d))throw new Error("Invalid username Alias - "+e+". Instead use "+Object.values(i.d))},T=function(e){var n=function(n){return s(void 0,void 0,void 0,(function(){var t,r;return _(this,(function(a){switch(a.label){case 0:switch(t=n.payload,t.event){case c.j:return[3,1]}return[3,8];case 1:if(!t.message)return[3,7];if(t.message!==i.a.SignedIn)return[3,6];a.label=2;case 2:return a.trys.push([2,4,,5]),[4,u.a.currentAuthenticatedUser()];case 3:return r=a.sent(),e(t.message,r),[3,5];case 4:return a.sent(),l.error("User is not authenticated"),[3,5];case 5:return[3,7];case 6:e(t.message,t.data),a.label=7;case 7:return[3,8];case 8:return[2]}}))}))};return a.a.listen(c.i,n),function(){return a.a.remove(c.i,n)}},O=function(e){return!(null===e.hint||"string"===typeof e.hint)},S={address:{label:o.a.get(E.a.ADDRESS_LABEL),placeholder:o.a.get(E.a.ADDRESS_PLACEHOLDER)},nickname:{label:o.a.get(E.a.NICKNAME_LABEL),placeholder:o.a.get(E.a.NICKNAME_PLACEHOLDER)},birthdate:{label:o.a.get(E.a.BIRTHDATE_LABEL),placeholder:o.a.get(E.a.BIRTHDATE_PLACEHOLDER)},phone_number:{label:o.a.get(E.a.PHONE_LABEL),placeholder:o.a.get(E.a.PHONE_PLACEHOLDER)},email:{lable:o.a.get(E.a.EMAIL_LABEL),placeholder:o.a.get(E.a.EMAIL_PLACEHOLDER)},picture:{label:o.a.get(E.a.PICTURE_LABEL),placeholder:o.a.get(E.a.PICTURE_PLACEHOLDER)},family_name:{label:o.a.get(E.a.FAMILY_NAME_LABEL),placeholder:o.a.get(E.a.FAMILY_NAME_PLACEHOLDER)},preferred_username:{label:o.a.get(E.a.PREFERRED_USERNAME_LABEL),placeholder:o.a.get(E.a.PREFERRED_USERNAME_PLACEHOLDER)},gender:{label:o.a.get(E.a.GENDER_LABEL),placeholder:o.a.get(E.a.GENDER_PLACEHOLDER)},profile:{label:o.a.get(E.a.PROFILE_LABEL),placeholder:o.a.get(E.a.PROFILE_PLACEHOLDER)},given_name:{label:o.a.get(E.a.GIVEN_NAME_LABEL),placeholder:o.a.get(E.a.GIVEN_NAME_LABEL)},zoneinfo:{label:o.a.get(E.a.ZONEINFO_LABEL),placeholder:o.a.get(E.a.ZONEINFO_PLACEHOLDER)},locale:{label:o.a.get(E.a.LOCALE_LABEL),placeholder:o.a.get(E.a.LOCALE_PLACEHOLDER)},updated_at:{label:o.a.get(E.a.UPDATED_AT_LABEL),placeholder:o.a.get(E.a.UPDATED_AT_PLACEHOLDER)},middle_name:{label:o.a.get(E.a.MIDDLE_NAME_LABEL),placeholder:o.a.get(E.a.MIDDLE_NAME_PLACEHOLDER)},website:{label:o.a.get(E.a.WEBSITE_LABEL),placeholder:o.a.get(E.a.WEBSITE_PLACEHOLDER)},name:{label:o.a.get(E.a.NAME_LABEL),placeholder:o.a.get(E.a.NAME_PLACEHOLDER)}};function C(e,n){var t=e.target.name,r=e.target.value;t===c.b&&(n.countryDialCodeValue=r),t===c.k&&(n.phoneNumberValue=r)}},325:function(e,n,t){"use strict";t.d(n,"a",(function(){return l})),t.d(n,"b",(function(){return f}));var r=t(38),a=t(22),o=t(321),i=t(86),u=t(319),E=t(322),c=function(e,n,t,r){return new(t||(t=Promise))((function(a,o){function i(e){try{E(r.next(e))}catch(n){o(n)}}function u(e){try{E(r.throw(e))}catch(n){o(n)}}function E(e){var n;e.done?a(e.value):(n=e.value,n instanceof t?n:new t((function(e){e(n)}))).then(i,u)}E((r=r.apply(e,n||[])).next())}))},s=function(e,n){var t,r,a,o,i={label:0,sent:function(){if(1&a[0])throw a[1];return a[1]},trys:[],ops:[]};return o={next:u(0),throw:u(1),return:u(2)},"function"===typeof Symbol&&(o[Symbol.iterator]=function(){return this}),o;function u(o){return function(u){return function(o){if(t)throw new TypeError("Generator is already executing.");for(;i;)try{if(t=1,r&&(a=2&o[0]?r.return:o[0]?r.throw||((a=r.return)&&a.call(r),0):r.next)&&!(a=a.call(r,o[1])).done)return a;switch(r=0,a&&(o=[2&o[0],a.value]),o[0]){case 0:case 1:a=o;break;case 4:return i.label++,{value:o[1],done:!1};case 5:i.label++,r=o[1],o=[0];continue;case 7:o=i.ops.pop(),i.trys.pop();continue;default:if(!(a=(a=i.trys).length>0&&a[a.length-1])&&(6===o[0]||2===o[0])){i=0;continue}if(3===o[0]&&(!a||o[1]>a[0]&&o[1]<a[3])){i.label=o[1];break}if(6===o[0]&&i.label<a[1]){i.label=a[1],a=o;break}if(a&&i.label<a[2]){i.label=a[2],i.ops.push(o);break}a[2]&&i.ops.pop(),i.trys.pop();continue}o=n.call(e,i)}catch(u){o=[6,u],r=0}finally{t=a=0}if(5&o[0])throw o[1];return{value:o[0]?o[1]:void 0,done:!0}}([o,u])}}},_=new r.a("auth-helpers");function l(e,n){return c(this,void 0,void 0,(function(){var t,r,c;return s(this,(function(s){switch(s.label){case 0:if(!i.a||"function"!==typeof i.a.verifiedContact)throw new Error(u.d);s.label=1;case 1:return s.trys.push([1,3,,4]),[4,i.a.verifiedContact(e)];case 2:return t=s.sent(),!Object(a.d)(t.verified)||Object(a.d)(t.unverified)?n(o.a.SignedIn,e):(r=Object.assign(e,t),n(o.a.VerifyContact,r)),[3,4];case 3:return c=s.sent(),Object(E.a)(c),[3,4];case 4:return[2]}}))}))}var f=function(e,n,t){return c(void 0,void 0,void 0,(function(){var r,a;return s(this,(function(c){switch(c.label){case 0:if(!i.a||"function"!==typeof i.a.signIn)throw new Error(u.d);c.label=1;case 1:return c.trys.push([1,9,,10]),[4,i.a.signIn(e,n)];case 2:return r=c.sent(),_.debug(r),r.challengeName!==o.b.SMSMFA&&r.challengeName!==o.b.SoftwareTokenMFA?[3,3]:(_.debug("confirm user with "+r.challengeName),t(o.a.ConfirmSignIn,r),[3,8]);case 3:return r.challengeName!==o.b.NewPasswordRequired?[3,4]:(_.debug("require new password",r.challengeParam),t(o.a.ResetPassword,r),[3,8]);case 4:return r.challengeName!==o.b.MFASetup?[3,5]:(_.debug("TOTP setup",r.challengeParam),t(o.a.TOTPSetup,r),[3,8]);case 5:return r.challengeName===o.b.CustomChallenge&&r.challengeParam&&"true"===r.challengeParam.trigger?(_.debug("custom challenge",r.challengeParam),t(o.a.CustomConfirmSignIn,r),[3,8]):[3,6];case 6:return[4,l(r,t)];case 7:c.sent(),c.label=8;case 8:return[3,10];case 9:return a=c.sent(),Object(E.a)(a),"UserNotConfirmedException"===a.code?(_.debug("the user is not confirmed"),t(o.a.ConfirmSignUp,{username:e})):"PasswordResetRequiredException"===a.code&&(_.debug("the user requires a new password"),t(o.a.ForgotPassword,{username:e})),[3,10];case 10:return[2]}}))}))}}}]);
//# sourceMappingURL=0.ed1bf9aa.chunk.js.map