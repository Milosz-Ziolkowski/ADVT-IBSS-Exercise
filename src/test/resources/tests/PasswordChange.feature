Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Scenario: Change password successfully
    Given the current username is "testuser"
    And the current password is "password123"
    When the user types the new password "NewPassword123"
    And the user retypes the new password "NewPassword123"
    Then the password is changed successfully

    Scenario: User cannot change password if incorrect credentials (username)
    Given the current username is "wronguser"
    And the current password is "password123"
    When the user types the new password "NewPassword123"
    And the user retypes the new password "NewPassword123"
    Then the password should not change
    And an error message is displayed indicating incorrect credentials

    Scenario: User cannot change password if incorrect credentials (password)
    Given the current username is "testuser"
    And the current password is "IncorrectPassword"
    When the user types the new password "NewPassword123"
    And the user retypes the new password "NewPassword123"
    Then the password should not change
    And an error message is displayed indicating incorrect credentials

    Scenario: User cannot change password with two different new passwords
    Given the current username is "testuser"
    And the current password is "password123"
    When the user types the new password "NewPassword123"
    And the user retypes the new password "DifferentPassword123"
    Then the password should not change
    And an error message is displayed indicating the passwords do not match

    Scenario: User cannot change password if new password does not meet policy requirements
    Given the current username is "testuser"
    And the current password is "password123"
    When the user types the new password "hi"
    And the user retypes the new password "hi"
    Then the password should not change
    And an error message is displayed indicating the password does not meet policy requirements

    Scenario: User cannot change password if new password is the same as the current password
    Given the current username is "testuser"
    And the current password is "password123"
    When the user types the new password "password123"
    And the user retypes the new password "password123"
    Then the password should not change
    And an error message is displayed indicating the new password cannot be the same as the current password

    Scenario: User cannot change password if new password is empty
    Given the current username is "testuser"
    And the current password is "password123"
    When the user types the new password ""
    And the user retypes the new password ""
    Then the password should not change
    And an error message is displayed indicating the new password cannot be empty