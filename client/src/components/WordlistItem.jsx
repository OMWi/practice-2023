import { ListItem, Paper, Stack, Typography } from "@mui/material";
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
      <Paper square sx={{ width: 1 }}>
        <Stack sx={{ padding: 1 }}>
          <Typography variant="h6">{wordlistData.name}</Typography>
          <Typography variant="subtitle2">
            Word List selected count: {wordlistData.popularity}
          </Typography>
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
