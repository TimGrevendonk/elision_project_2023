const DUMMY_CATEGORIES = [
  {
    id: '1',
    name: 'Smartphones',
    description: 'Need a new phone? This is where you can find all smartphones in our stock.'
  },
  {
    id: '2',
    name: 'PCs and Tablets',
    description: 'BYOD. This is where you can find all the personal computers and tablets in our stock.'
  },
  {
    id: '3',
    name: 'PC Components',
    description: 'Looking to build anew? Or maybe to upgrade? This is where you can find all the PC components in our stock.'
  }
]

export function getAllCategories() {
  return DUMMY_CATEGORIES;
}

export function getCategoryById(id) {
  return DUMMY_CATEGORIES.find((category) => category.id === id);
}