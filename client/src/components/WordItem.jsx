import { ListItem, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";

export default function WordItem({ wordData }) {
  return (
    <ListItem disablePadding>
      <Stack>
        <Typography variant="h6">{wordData.text}</Typography>
        <Typography variant="subtitle2">{wordData.category}</Typography>
      </Stack>
    </ListItem>
  );
}

WordItem.propTypes = {
  wordData: PropTypes.shape({
    text: PropTypes.string,
    category: PropTypes.string,
  }),
};
