export const tableTitleColumns = [
  { title: 'Name', field: 'name' },
  { title: 'Year', field: 'year', type: 'numeric' },
  {
    title: 'Genre',
    field: 'genre',
    lookup: { 'COMEDY':'Comedy', 'Action': 'Action' },

  },
  {
    title: 'Picture',
    field: 'picture_path'
  },
  {
    title: 'Trailer',
    field: 'trailer_url',
  },

  {
    title: 'Actors',
    field: 'actors',
  }

  
]