import { ListItem, Paper, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";

export default function WordItem({ wordlistData }) {
  return (
    <ListItem disablePadding sx={{ mb: 1 }}>
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

WordItem.propTypes = {
  wordlistData: PropTypes.shape({
    name: PropTypes.string,
    popularity: PropTypes.number,
  }),
};
