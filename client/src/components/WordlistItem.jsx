import { Chip, ListItem, Paper, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

export default function WordlistItem({ wordlistData }) {
  const navigate = useNavigate();

  return (
    <ListItem
      disablePadding
      sx={{
        mb: 1,
        "&:hover": {
          cursor: "pointer",
        },
      }}
      onClick={() => navigate(`/word-lists/${wordlistData.id}`)}
    >
      <Paper
        square
        sx={{
          width: 1,
          "&:hover": {
            bgcolor: "selected.main",
          },
        }}
      >
        <Stack sx={{ padding: 1 }} direction="row">
          <Stack
            direction="row"
            alignItems="center"
            spacing={1}
            sx={{ flexGrow: 1 }}
          >
            <Chip
              size="small"
              label={wordlistData.difficulty}
              variant="outlined"
            />
            <Typography variant="h6">{wordlistData.name}</Typography>
          </Stack>

          <Stack>
            <Typography variant="body2" textAlign="end">
              Bookmarked: {wordlistData.popularity} times
            </Typography>
            <Typography variant="body2" textAlign="end">
              Favorited: {wordlistData.popularity} times
            </Typography>
          </Stack>
        </Stack>
      </Paper>
    </ListItem>
  );
}

WordlistItem.propTypes = {
  wordlistData: PropTypes.shape({
    id: PropTypes.number,
    name: PropTypes.string,
    popularity: PropTypes.number,
  }),
};
