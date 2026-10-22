# Contributing

## Issue-based development

Read the Issue, dependencies and acceptance criteria before implementation.
Confirm ownership with the Project Lead. Keep each change focused on one Issue
and agree on shared contracts before depending on another workstream.

Start from a clean, current `main`:

```sh
git switch main
git pull --ff-only
git switch -c issue-<number>-<topic>
```

Replace the branch-name placeholders with the real Issue number and topic.
Use short-lived branches; do not maintain permanent workstream branches.
Preserve unexpected local changes before switching or integrating work.

## Implementation and testing

- Keep JavaFX controls and event handling in `ui`; keep future conversion rules
  and HTTP integration in their separate areas.
- Do not block the JavaFX application thread with HTTP requests or waiting for
  background work.
- Add meaningful JUnit tests for new behavior. Tests must use deterministic
  data and must not depend on the live rate service in ordinary CI.
- Run `./mvnw --batch-mode --no-transfer-progress clean verify` on Linux or
  `.\mvnw.cmd --batch-mode --no-transfer-progress clean verify` on Windows.
- For GUI changes, launch the application and record relevant keyboard, resize,
  error-state and shutdown checks. Windows-specific claims require Windows checks.
- Review `git diff --check`, the full diff and `git status` before committing.
  Never commit credentials, caches, IDE settings, logs or generated build outputs.

## Commits, PRs and review

Use professional English and Conventional Commits, such as
`feat: validate conversion amount` or `test: cover rounding boundaries`.
Commit under your own identity and describe the actual change.

Open a PR to `main` using the template. Link the Issue, explain behavior changes,
report checks actually run, and identify skipped checks and known limitations.
Request review from another team member and address feedback before integration.
The Project Lead coordinates integration once review and CI are complete.

An Issue is complete when its acceptance criteria, relevant tests, required
manual checks and documentation are satisfied. A passing build alone does not
verify GUI operation or Windows packaging.

## Contribution records

Record actual implementation, tests, reviews, integration and pairing work.
Confirm contributor identities and credit with the people involved. Do not
attribute work to another student, invent activity, or mark assignments complete
before their acceptance criteria are met. Follow the course's submission rules.
