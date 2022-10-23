import common from 'common/frontend/common-eslint/eslint2.config.js'
import prettier from 'eslint-config-prettier'
import turbo from 'eslint-config-turbo'
import svelte from 'eslint-plugin-svelte3'

import typescript from '@typescript-eslint/eslint-plugin'
import typescriptParser from '@typescript-eslint/parser'

export default [
  common,
  typescript.configs.recommended,
  //prettier,
  //turbo,
  {
    ignores: ['**/.svelte-kit/**', '**/.turbo/**', '**/build/**', '**/dist/*.js'],
    files: ['**/*.js', '**/*.ts', '**/*.svelte'],
    languageOptions: {
      ecmaVersion: 'latest',
      parser: typescriptParser,
      sourceType: 'module'
    },
    plugins: {
      typescript
    }
   },
  {
    ignores: ['**/.svelte-kit/**', '**/.turbo/**', '**/build/**', '**/dist/**'],
    files: ['**/*.svelte'],
    plugins: {
      svelte
    },
    processor: 'svelte/svelte3',
    settings: {
      'svelte3/typescript': () => require('typescript')
    }
  }
]